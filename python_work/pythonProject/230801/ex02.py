from sklearn.datasets import fetch_20newsgroups
import pandas as pd
'''
뉴스들의 문서를 보고 분류 하는 프로그램 
20가지의 target 데이터가 있음.
'''

news_data = fetch_20newsgroups(subset='all',random_state=42)
print(news_data.keys())

print('target 클래스값 분포도\n',pd.Series(news_data.target).value_counts())
print('target 클래스값 이름\n',news_data.target_names)
'''
1. 'alt.atheism', 2. 'comp.graphics', 3. 'comp.os.ms-windows.misc', 
4. 'comp.sys.ibm.pc.hardware', 5. 'comp.sys.mac.hardware', 6. 'comp.windows.x', 
7. 'misc.forsale', 8.'rec.autos', 9.'rec.motorcycles', 10.'rec.sport.baseball',
12.'rec.sport.hockey', 13.'sci.crypt', 14.'sci.electronics', 
15.'sci.med', 16.'sci.space', 17.'soc.religion.christian', 18.'talk.politics.guns',
19.'talk.politics.mideast', 20.'talk.politics.misc', 21.'talk.religion.misc'
'''

'''
    i am a teacher.
    i am a student.
    i am a progammer.
    
'''
# print(news_data.data[4],news_data.target[4],news_data.target_names[4])
# print(news_data.data[5],news_data.target[5],news_data.target_names[5])
# print(news_data.data[6],news_data.target[6],news_data.target_names[6])
train_news = fetch_20newsgroups(subset='train',remove={'headers','footers','quotes'},random_state=42)
x_train = train_news.data
y_train = train_news.target
test_news = fetch_20newsgroups(subset='test',remove={'headers','footers','quotes'},random_state=42)
x_test = test_news.data
y_test = test_news.target

from sklearn.feature_extraction.text import CountVectorizer

cnt_vect= CountVectorizer()
cnt_vect.fit(x_train)
x_train_vec = cnt_vect.transform(x_train)
x_test_vec = cnt_vect.transform(x_test)

print(x_train_vec[0])
print(x_train_vec[0].todense())
# print(cnt_vect.vocabulary_)
print(x_train_vec.shape)

# cnt = CountVectorizer()
# train_data = ['i am a teacher.','i am a student.','i am a progammer.']
# cnt.fit(train_data)
# train_vec = cnt.transform(train_data)
# print(train_vec)
# print(train_vec.todense())
# print(cnt.vocabulary_)
