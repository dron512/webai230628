from nltk import word_tokenize
from konlpy.tag import Okt
okt = Okt()

text_sample = '안녕하세요 한글입니다. 특별한 의미 있는 문장은 아니예요 샘플입니다. 그냥 적어보께요'

words = word_tokenize(text=text_sample)
print(words)

words = okt.morphs(text_sample)
print(words)

from nltk.stem import LancasterStemmer
stemmer = LancasterStemmer()

work = stemmer.stem('working')
print(work)
work = stemmer.stem('works')
print(work)
work = stemmer.stem('worked')
print(work)

# 1. 클렌징 <html> <br>
# 2. word 토큰화
# 3. working works worked -> work



