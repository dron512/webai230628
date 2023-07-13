import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_validate

from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import ExtraTreesClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import HistGradientBoostingClassifier
from xgboost import XGBClassifier
from lightgbm import LGBMClassifier

wine = pd.read_csv('https://bit.ly/wine_csv_data')

data = wine[['alcohol', 'sugar', 'pH']].to_numpy()
target = wine['class'].to_numpy()

train_input, test_input, train_target, test_target =\
    train_test_split(data, target, test_size=0.2, random_state=42)

rfclf = RandomForestClassifier(n_jobs=-1,random_state=42)
rfclf.fit(train_input,train_target)


# scores = cross_validate(RandomForestClassifier(n_jobs=-1,random_state=42),
#                         train_input,train_target,return_train_score=True)
# print(scores)

# scores = cross_validate(ExtraTreesClassifier(n_jobs=-1,random_state=42),
#                         train_input,train_target,return_train_score=True)
# print(scores)
# scores = cross_validate(GradientBoostingClassifier(),
#                         train_input,train_target,return_train_score=True)
# print(scores)
# scores = cross_validate(HistGradientBoostingClassifier(),
#                         train_input,train_target,return_train_score=True)
# print(scores)
# scores = cross_validate(XGBClassifier(),
#                         train_input,train_target,return_train_score=True)
# print(scores)
# scores = cross_validate(LGBMClassifier(),
#                         train_input,train_target,return_train_score=True)
# print(scores)













