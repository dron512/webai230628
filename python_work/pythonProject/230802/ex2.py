from sklearn.metrics.pairwise import cosine_similarity
from konlpy.tag import Okt
from sklearn.feature_extraction.text import CountVectorizer

a = '장바구니 투명 마트 휴대용 코스트코 이케아 타포린 백 가방 쇼핑백 네이터 쇼핑 시장'
b = '새길무역 타포린백 쇼핑 마트시장 가방 네이버 쇼핑 장바구니 주문제작 에코백 빨래방'
c = '리유즈 프로듀스 백 명품 종이 쇼핑백 마트 장바구니 장가방 네이터쇼핑 대형 시장가방'
d = '휴대용 코스트코 이케아 타포린 백'
e = '마트시장 가방 네이버 쇼핑 장바구니 '

doc_list = [a,b,c,d,e]
okt = Okt()
def tw_tokenize(text):
    test = okt.morphs(text)
    return test

cvt = CountVectorizer(tokenizer=tw_tokenize,ngram_range=(1,2))

cvt.fit(doc_list)
doc_vec = cvt.transform(doc_list)
print(doc_vec)
print(doc_vec.todense())

유사도 = cosine_similarity(doc_vec,doc_vec)
print(유사도)

sorted_index = 유사도.argsort()[:,::-1]
print(sorted_index)

sorted_index = sorted_index[:, 1:]
print(sorted_index)

import numpy as np

doc_list = np.array(doc_list)
print(doc_list[sorted_index[0]])





