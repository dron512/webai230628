'''
flask 
웹 프로그래밍
1. 수치예측..(수치분류,수치예측)
3 5 -> ?
2. 파일이미지
파일 업로드 분류

3. 추천 시스템
영화 데이터 tmdb 영화데이셋
sf 조인성 -> sf 영화중에서 조인성 평점 높은 순 추천해줌
'''
import numpy as np
import tensorflow as tf
from tensorflow import keras
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
import cv2

# 0     1   2       3   4    5    6     7    8    9
# 티셔츠 바지 스웨터 드레스 코트 샌달 셔츠 스니커즈 가방 앵글부츠
target = ['티셔츠', '바지', '스웨터' ,'드레스',
          '코트', '샌달', '셔츠', '스니커즈' ,'가방','앵글부츠']
model = keras.models.load_model('best-cnn-model.h5')
for i in range(10):
    a0 = cv2.imread(f'a{i}.png', cv2.IMREAD_GRAYSCALE)
    a0 = a0 / 255
    a0 = a0.reshape(1, 28, 28, 1)
    pred = model.predict(a0)
    print(pred)
    print(f'{i} 번째 이미지는 ',target[np.argmax(pred)])