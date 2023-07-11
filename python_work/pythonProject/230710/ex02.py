import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split

# ctrl + shift + f10 실행 단축키

wine = pd.read_csv('https://bit.ly/wine_csv_data')
print(wine.head())
print(wine.info())
print(wine.isnull().sum())
print(wine.describe())

input_data = wine[['alcohol', 'sugar', 'pH']].to_numpy()
target_data = wine['class'].to_numpy()

print(input_data.shape)
print(target_data.shape)

train_input, test_input, train_target, test_target = \
    train_test_split(input_data, target_data, random_state=42)

print(train_input.shape)
print(test_input.shape)

ss = StandardScaler()
ss.fit(train_input)

train_scaled = ss.transform(train_input)
test_scaled = ss.transform(test_input)

# print(train_scaled[:5])
# print(test_scaled[:5])

lr = LogisticRegression()
lr.fit(train_scaled, train_target)

train_score = lr.score(train_scaled, train_target)
test_score = lr.score(test_scaled, test_target)

print(train_score)
print(test_score)

# target 1 1 1
val_data = [[9.0, 14.9, 3.13], [10.6, 6.2, 3.6], [9.5, 8.5, 3.32]]

val_data = ss.transform(val_data)

predvalue = lr.predict(val_data)
print(predvalue)

from sklearn.tree import DecisionTreeClassifier

dclf = DecisionTreeClassifier(max_depth=3,
                              min_impurity_decrease=0.005,
                              random_state=42)
dclf.fit(train_scaled,train_target)

train_score = dclf.score(train_scaled,train_target)
test_score = dclf.score(test_scaled,test_target)

print(train_score)
print(test_score)

import matplotlib.pyplot as plt
from sklearn.tree import plot_tree

plt.figure(figsize=(10,7))
plot_tree(dclf,
          filled=True,
          feature_names=['alcohol','sugar','pH'])
plt.show()










