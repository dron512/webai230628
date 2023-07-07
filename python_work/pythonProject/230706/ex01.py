from sklearn.linear_model import LinearRegression
from sklearn.linear_model import Ridge
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import PolynomialFeatures
import numpy as np

train_data = np.array([[2, 5], [3, 6], [4, 8], [5, 10], [6, 13]])
test_data = np.array([[3, 7], [2, 9], [5, 11]])
target = np.array([10, 18, 32, 50, 78])
test_target = np.array([21,18,55])

lr = LinearRegression()
lr.fit(train_data, target)

predTrain = lr.predict(train_data)
predTest = lr.predict(test_data)

print(predTrain)
print(predTest)

poly = PolynomialFeatures(include_bias=False,degree=5)
poly.fit(train_data)
train_poly = poly.transform(train_data)
test_poly = poly.transform(test_data)
print(train_poly)

lr = LinearRegression()
lr.fit(train_poly,target)

predTrain = lr.predict(train_poly)
predTest = lr.predict(test_poly)

print(predTrain)
print(predTest)

ss = StandardScaler()
ss.fit(train_poly)

train_scaled = ss.transform(train_poly)
test_scaled = ss.transform(test_poly)

ridge = Ridge(alpha=0.001)
ridge.fit(train_scaled,target)

predTrain = ridge.predict(train_scaled)
predTest = ridge.predict(test_scaled)

print(predTrain)
print(predTest)
















