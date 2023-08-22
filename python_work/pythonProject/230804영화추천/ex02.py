from flask import Flask,render_template
import ex01

app = Flask(__name__)

@app.route("/")
@app.route("/<name>")
def index(name='Avatar'):
    if name :
        df = ex01.find_sim_moive(title_name=name)
    else:
        df = ex01.find_sim_moive()
    df = df[['title','genres_literal','vote_count','vote_average']]
    table_html = df.to_html(classes='table table-striped',justify='left')
    data= {}
    data['title'] = ex01.movies_df['title']
    data['table_html'] = table_html
    data['name'] = name
    # table_html = df.to_html(classes='table table-striped')
    return render_template('index.html',data=data)

app.run(debug=True,host='0.0.0.0')