from nltk import sent_tokenize
from nltk import word_tokenize
import nltk
# nltk.download('punkt')
text_sample = 'The Matrix is everywhere. It is all around us. ' \
              'Even now, in this very room. You can see it when' \
              ' you look out your window or when you turn on your television.' \
              ' You can feel it when you go to work... w' \
              'hen you go to church... when you pay your taxes. ' \
              'It is the world that has been pulled over your eyes to blind you from the truth.'
sentences = sent_tokenize(text=text_sample)
words = word_tokenize(text=text_sample)
print(type(sentences),len(sentences))
print(sentences)
print(type(words),len(words))
print(words)

nltk.download('stopwords')

eng_stop = nltk.corpus.stopwords.words('english')
print(len(eng_stop))
print(eng_stop)

filter_word = []
for word in words:
    word = word.lower()
    if word not in eng_stop:
        filter_word.append(word)
print(filter_word)