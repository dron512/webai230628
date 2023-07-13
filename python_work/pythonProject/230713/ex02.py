from flask import Flask, request
from ex01 import rfclf

app = Flask(__name__)

@app.route("/")
def index():
    alcohol = request.args.get("alcohol")
    sugar = request.args.get("sugar")
    pH = request.args.get("pH")

    if alcohol :
        predValue = rfclf.predict([[float(alcohol),float(sugar),float(pH)]])
        return str(predValue)
    else:
        return "예측할값을 입력하세요"

app.run(debug=True)