import numpy as np
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import cross_validate

fruits = np.load('fruits_300.npy')
print(fruits.shape)

fruits_2d = fruits.reshape(-1, 100 * 100)
print(fruits_2d.shape)

pca = PCA(n_components=50)
pca.fit(fruits_2d)

print(pca.components_.shape)

주성분50개이미지 = pca.components_.reshape(-1, 100, 100)

fig, axs = plt.subplots(5, 10, squeeze=False)

for i in range(5):
    for j in range(10):
        axs[i, j].imshow(주성분50개이미지[i * 10 + j], cmap='gray_r')
        axs[i, j].axis('off')
plt.show()

fruits_pca = pca.transform(fruits_2d)
fruits_inverse = pca.inverse_transform(fruits_pca)

print(fruits_2d.shape)
print(fruits_pca.shape)
print(fruits_inverse.shape)

fruits_inverse = fruits_inverse.reshape(-1, 100, 100)
print(fruits_inverse.shape)

fig, axs = plt.subplots(10, 10, squeeze=False)
for i in range(10):
    for j in range(10):
        axs[i, j].imshow(fruits_inverse[i * 10 + j], cmap='gray_r')
        axs[i, j].axis('off')
plt.show()

fig, axs = plt.subplots(10, 10, squeeze=False)
for i in range(10):
    for j in range(10):
        axs[i, j].imshow(fruits_inverse[(i + 10) * 10 + j], cmap='gray_r')
        axs[i, j].axis('off')
plt.show()

fig, axs = plt.subplots(10, 10, squeeze=False)
for i in range(10):
    for j in range(10):
        axs[i, j].imshow(fruits_inverse[(i + 20) * 10 + j], cmap='gray_r')
        axs[i, j].axis('off')
plt.show()

lr = LogisticRegression()

target = ['사과']*100+['파인애플']*100+['바나나']*100

scores = cross_validate(lr,fruits_2d,target)
print(np.mean(scores['test_score']))
print(np.mean(scores['fit_time']))

scores = cross_validate(lr,fruits_pca,target)
print(np.mean(scores['test_score']))
print(np.mean(scores['fit_time']))

pca = PCA(n_components=0.5)
pca.fit(fruits_2d)

fruits_pca = pca.transform(fruits_2d)
print(fruits_pca.shape)

scores = cross_validate(lr,fruits_pca,target)
print(np.mean(scores['test_score']))
print(np.mean(scores['fit_time']))

from sklearn.cluster import KMeans

kmeans = KMeans(n_clusters=3,random_state=42)
kmeans.fit(fruits_pca)

for label in range(0,3):
    data = fruits_pca[kmeans.labels_ ==label]
    plt.scatter(data[:,0],data[:,1])
    plt.legend(['apple','banana','pineapple'])
plt.show()





