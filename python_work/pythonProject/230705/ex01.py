import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures

# data = pd.read_excel('aa.xlsx')
data = pd.read_csv('https://bit.ly/perch_csv_data')
perch_full = data.to_numpy()
# print(perch_full)

perch_weight = np.array(
    [5.9, 32.0, 40.0, 51.5, 70.0, 100.0, 78.0, 80.0, 85.0, 85.0,
     110.0, 115.0, 125.0, 130.0, 120.0, 120.0, 130.0, 135.0, 110.0,
     130.0, 150.0, 145.0, 150.0, 170.0, 225.0, 145.0, 188.0, 180.0,
     197.0, 218.0, 300.0, 260.0, 265.0, 250.0, 250.0, 300.0, 320.0,
     514.0, 556.0, 840.0, 685.0, 700.0, 700.0, 690.0, 900.0, 650.0,
     820.0, 850.0, 900.0, 1015.0, 820.0, 1100.0, 1000.0, 1100.0,
     1000.0, 1000.0]
 )
train_input,test_input,train_target,test_target = train_test_split(
    perch_full,perch_weight,random_state=42
)

lr = LinearRegression()
lr.fit(train_input,train_target)

score = lr.score(test_input,test_target)
print(score)

polynomial = PolynomialFeatures(include_bias=False)
polynomial.fit([[2,3]])
print( polynomial.transform([[2,3]]) )

polynomial.fit(train_input)
train_poly = polynomial.transform(train_input)
test_poly = polynomial.transform(test_input)

# print(train_input[0])
# print(train_poly[0])

lr.fit(train_poly,train_target)
score = lr.score(test_poly,test_target)
print(score)

polynomial = PolynomialFeatures(degree=5,include_bias=False)
polynomial.fit(train_input)
train_poly = polynomial.transform(train_input)
test_poly = polynomial.transform(test_input)

print(train_input.shape)
print(train_poly[0].shape)
print(test_poly[0].shape)

lr.fit(train_poly,train_target)
score = lr.score(train_poly,train_target)
print(score)
score = lr.score(test_poly,test_target)
print(score)

from sklearn.preprocessing import StandardScaler

ss = StandardScaler()
ss.fit(train_poly)
train_scaled = ss.transform(train_poly)
test_scaled = ss.transform(test_poly)

from sklearn.linear_model import Ridge

ridge = Ridge()
ridge.fit(train_scaled,train_target)
score = ridge.score(train_scaled,train_target)
print(score)
score = ridge.score(test_scaled,test_target)
print(score)

train_score = []
test_score = []

for i in [0.001,0.01,0.1,1,10,100]:
    ridge = Ridge(alpha=i)
    ridge.fit(train_scaled, train_target)
    train_score.append(ridge.score(train_scaled, train_target))
    test_score.append(ridge.score(test_scaled, test_target))

print(train_score)
print(test_score)

import matplotlib.pyplot as plt

plt.plot( np.log10([0.001,0.01,0.1,1,10,100]),train_score)
plt.plot( np.log10([0.001,0.01,0.1,1,10,100]),test_score)
plt.show()

# fit
# predict
# score

from sklearn.linear_model import Lasso
train_score = []
test_score = []
for i in [0.001,0.01,0.1,1,10,100]:
    lasso = Lasso(alpha=i)
    lasso.fit(train_scaled, train_target)
    train_score.append(lasso.score(train_scaled, train_target))
    test_score.append(lasso.score(test_scaled, test_target))

import matplotlib.pyplot as plt

plt.plot( np.log10([0.001,0.01,0.1,1,10,100]),train_score)
plt.plot( np.log10([0.001,0.01,0.1,1,10,100]),test_score)
plt.show()

lasso = Lasso()
lasso.fit(train_scaled, train_target)
print(np.sum([1,2,3,4,5,6,7,8,9]))
print(np.sum(lasso.coef_ ==0 ))







