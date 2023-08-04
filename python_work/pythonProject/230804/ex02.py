from flask import Flask,render_template
import ex01

app = Flask(__name__)

@app.route("/<name>")
def index(name):
    if name :
        df = ex01.find_sim_moive(title_name=name)
    else:
        df = ex01.find_sim_moive()
    df = df[['title','genres_literal','vote_count','vote_average']]
    table_html = df.to_html(classes='table table-striped')
    print(table_html)
    # table_html = df.to_html(classes='table table-striped')
    return render_template('index.html',table_html=table_html)

app.run(debug=True,host='0.0.0.0')