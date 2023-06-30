from sklearn.neighbors import KNeighborsClassifier

knclf = KNeighborsClassifier(n_neighbors=2)

data = [[1,2],[2,3],[4,5],[10,11],[11,12],[12,13]]
target = ['a','a','a','b','b','b']

knclf.fit(data,target)

predvalue = knclf.predict([[10,11],[2,3]])
print(predvalue)

def doA(x,y):
    return knclf.predict([[x,y]])