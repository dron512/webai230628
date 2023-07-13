import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_validate
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import GridSearchCV

wine = pd.read_csv('https://bit.ly/wine_csv_data')
data = wine[['alcohol', 'sugar', 'pH']].to_numpy()
target = wine['class'].to_numpy()

print(data.shape)
print(target.shape)

train_input, test_input, train_target, test_target = \
    train_test_split(data, target, random_state=42)

# for i in range(3,10):
dtclf = DecisionTreeClassifier(max_depth=3,min_impurity_decrease=0.001)
dtclf.fit(train_input, train_target)

train_score = dtclf.score(train_input, train_target)
test_score = dtclf.score(test_input, test_target)

print(train_score)
print(test_score)

predValue = dtclf.predict([[9.4, 1.9, 3.51], [9.4, 2.5, 3.52]])
print(predValue)

scores = cross_validate(dtclf, train_input, train_target)
print(scores)

from sklearn.model_selection import StratifiedKFold

myfold = StratifiedKFold(n_splits=10, random_state=42, shuffle=True)
scores = cross_validate(dtclf, train_input, train_target, cv=myfold)
print(scores)

params = {"min_impurity_decrease":[0.0001,0.0002,0.0003,0.0004,0.0005]}
gv = GridSearchCV(DecisionTreeClassifier(random_state=42),params, n_jobs=-1)

gv.fit(train_input,train_target)

dt = gv.best_estimator_
score = dt.score(train_input,train_target)
print(score)

best_param = gv.best_params_
print(best_param)


