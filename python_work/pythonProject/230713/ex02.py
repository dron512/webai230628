from flask import Flask, send_file, request, render_template, make_response

from io import BytesIO, StringIO
import numpy as np

## macOS의 경우 아래 순서에 따라서 library를 import해줘야 에러없이 잘 됩니다.
import matplotlib

matplotlib.use('Agg')
import matplotlib.pyplot as plt

from ex01 import rfclf
from flask_cors import CORS
from functools import wraps, update_wrapper
from datetime import datetime

app = Flask(__name__)

CORS(app)


def nocache(view):
    @wraps(view)
    def no_cache(*args, **kwargs):
        response = make_response(view(*args, **kwargs))
        response.headers['Last-Modified'] = datetime.now()
        response.headers['Cache-Control'] = 'no-store, no-cache, must-revalidate, post-check=0, pre-check=0, max-age=0'
        response.headers['Pragma'] = 'no-cache'
        response.headers['Expires'] = '-1'
        return response

    return update_wrapper(no_cache, view)


@app.route("/")
def index():
    alcohol = request.args.get("alcohol")
    sugar = request.args.get("sugar")
    pH = request.args.get("pH")
    print(f"alcohol = {alcohol} sugar={sugar} pH={pH}")

    if alcohol:
        predValue = rfclf.predict([[float(alcohol), float(sugar), float(pH)]])
        return str(predValue)
    else:
        return "예측할값을 입력하세요"


@app.route('/fig/<int:mean>_<int:var>')
@nocache
def fig(mean, var):
    plt.figure(figsize=(4, 3))
    xs = np.random.normal(mean, var, 100)
    ys = np.random.normal(mean, var, 100)
    plt.scatter(xs, ys, s=100, marker='h', color='red', alpha=0.3)
    """
    file로 저장하는 것이 아니라 binary object에 저장해서 그대로 file을 넘겨준다고 생각하면 됨
    """
    img = BytesIO()
    plt.savefig(img, format='png', dpi=100)
    img.seek(0)  ## object를 읽었기 때문에 처음으로 돌아가줌
    plt.close()
    return send_file(img, mimetype='image/png')
    # plt.savefig(img, format='svg')
    # return send_file(img, mimetype='image/svg')


@app.route('/fie/<int:a>_<int:b>_<int:c>_<int:d>')
@nocache
def fie(a, b, c, d):
    ratio = [a, b, c, d]
    labels = ['Apple', 'Banana', 'Melon', 'Grapes']
    plt.pie(ratio, labels=labels, autopct='%.1f%%')
    img = BytesIO()
    plt.savefig(img, format='png', dpi=100)
    img.seek(0)
    plt.close()
    return send_file(img, mimetype='image/png')

app.run(debug=True, host="0.0.0.0")
