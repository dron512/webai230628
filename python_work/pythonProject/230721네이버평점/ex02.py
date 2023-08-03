import pickle
from konlpy.tag import Okt
import pandas as pd
import re
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import GridSearchCV

okt = Okt()

def tw_tokenizer(text):
    # 입력 인자로 들어온 text 를 형태소 단어로 토큰화 하여 list 객체 반환
    tokens_ko = okt.morphs(text)
    return tokens_ko

with open('saved_model.pickle','rb') as f:
    estimator = pickle.load(f)

with open('tfidf_vect.pickle', 'rb') as fw:
    tfidf_vect = pickle.load(fw)

# train_df = pd.read_csv('./ratings_train.txt', sep='\t')
# print(train_df.head(3))
# print(train_df['label'].value_counts())
#
# train_df = train_df.fillna(' ')
# # 정규 표현식을 이용하여 숫자를 공백으로 변경(정규 표현식으로 \d 는 숫자를 의미함.)
# train_df['document'] = train_df['document'].apply( lambda x : re.sub(r"\d+", " ", x) )
#
# # 테스트 데이터 셋을 로딩하고 동일하게 Null 및 숫자를 공백으로 변환
# test_df = pd.read_csv('ratings_test.txt', sep='\t')
# test_df = test_df.fillna(' ')
# test_df['document'] = test_df['document'].apply( lambda x : re.sub(r"\d+", " ", x) )
#
# # id 칼럼 삭제 수행
# train_df.drop('id', axis=1, inplace=True)
# test_df.drop('id', axis=1, inplace=True)
#
# print(train_df.head(3))

# Twitter 객체의 morphs( ) 객체를 이용한 tokenizer를 사용. ngram_range는 (1,2)
# tfidf_matrix_train = tfidf_vect.transform(train_df['document'])
#
# print(tfidf_matrix_train[:10])
# train_df['document'] = train_df['document'].apply( lambda x : re.sub(r"\d+", " ", x) )

test =[]
test.append("이 영화 별로다")      #0
test.append("이 영화 진짜 좋타")   #1
test.append("이 영화 진짜 짱짱")   #1
test.append("이 영화 진짜 싫어")   #0
test.append("이 영화 진짜 좋음")   #1
test.append("이 영화 진짜 구림")   #0

test = tfidf_vect.transform(test)
# print(test)

pred = estimator.predict(test)
print(pred)
