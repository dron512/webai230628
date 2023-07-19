import numpy as np
import tensorflow as tf

products = [
    {"id": 1, "features": [0.2, 0.5, 0.1]},
    {"id": 2, "features": [0.4, 0.3, 0.9]},
    {"id": 3, "features": [0.1, 0.8, 0.2]},
    {"id": 4, "features": [0.6, 0.2, 0.5]}
]

# 상품 특성 정보를 나타내는 벡터로 가정
product_features = np.array([
    [0.2, 0.5, 0.1],
    [0.4, 0.3, 0.9],
    [0.1, 0.8, 0.2],
    [0.6, 0.2, 0.5]
])

# 새로운 사용자에게 추천할 상품
new_user_features = np.array([0.3, 0.4, 0.7])

# 코사인 유사도 계산
norm_product_features = tf.nn.l2_normalize(product_features, axis=1)
norm_new_user_features = tf.nn.l2_normalize(new_user_features, axis=0)
similarity_scores = tf.matmul(norm_product_features, norm_new_user_features)

# 유사도가 가장 높은 상품 추천
top_k = tf.argmax(similarity_scores, axis=0, k=3)

with tf.Session() as sess:
    top_k_indices = sess.run(top_k)
    recommended_products = [product for idx, product in enumerate(products) if idx in top_k_indices]

print("추천 상품:", recommended_products)
