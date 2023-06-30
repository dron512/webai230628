from flask import Flask,render_template,request
import ex01

app = Flask(__name__)

@app.route("/")
def index():
    return "index"

@app.route("/aa",methods=['GET','POST'])
def aa():
    result = 10
    if request.method == 'POST':
        x = request.form['x']
        y = request.form['y']
        result = ex01.doA(int(x), int(y))
    elif request.method =='GET':
        print("GET")
    return render_template("aa.html",result=result)

app.run(host='127.0.0.1',debug=True)