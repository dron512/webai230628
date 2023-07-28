from sklearn.feature_extraction.text import  CountVectorizer
from nltk.text import sent_tokenize

text_sample = 'The Matrix is everywhere. It is all around us. ' \
              'Even now, in this very room. You can see it when' \
              ' you look out your window or when you turn on your television.' \
              ' You can feel it when you go to work... w' \
              'hen you go to church... when you pay your taxes. ' \
              'It is the world that has been pulled over your eyes to blind you from the truth.'
text_sample = sent_tokenize(text_sample)
print(text_sample)
vector = CountVectorizer()
train = vector.fit_transform(text_sample)
print(train)
print(train.toarray())
print(train.todense())