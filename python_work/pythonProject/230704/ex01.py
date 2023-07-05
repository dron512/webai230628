from sklearn.neighbors import KNeighborsRegressor
import numpy as np
'''
    3시간 공부하면 60점
    10시간 공부하면 70점
    20시간 공부하면 80점
    30시간 공부하면 90점
    40시간 공부하면 100점인데
    35시간 공부했을때 예상되는 점수는?
    최근접 이웃 회귀로 풀어보세요
'''
x = [3, 10, 20, 30, 40]
y = [60, 70, 80, 90, 100]

x = np.array(x)
x = x.reshape(-1,1)
# print(x)

knr = KNeighborsRegressor(n_neighbors=2)
knr.fit(x,y)

predValue = knr.predict([[35]])
print(predValue)
