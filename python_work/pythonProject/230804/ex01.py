import pandas as pd
import numpy as np
import warnings; warnings.filterwarnings('ignore')

movies = pd.read_csv('./tmdb_5000_movies.csv')
movies_df = movies[['id','title','genres','vote_average','vote_count','popularity','keywords','overview']]

from ast import literal_eval

movies_df['genres'] = movies_df['genres'].apply(literal_eval)
# movies_df['keywords'] = movies_df['keywords'].apply(literal_eval)

# 장르에 id 지우기
movies_df['genres'] = movies_df['genres'].apply(lambda x:[y['name'] for y in x])
# movies_df['keywords'] = movies_df['keywords'].apply(lambda x:[y['name'] for y in x])

# 쉼표 지우기
movies_df['genres_literal'] = movies_df['genres'].apply(lambda x:(' ').join(x))

from sklearn.feature_extraction.text import CountVectorizer

count_vect = CountVectorizer(min_df=0,ngram_range=(1,2))
genre_mat = count_vect.fit_transform(movies_df['genres_literal'])

from sklearn.metrics.pairwise import cosine_similarity

genre_sim = cosine_similarity(genre_mat,genre_mat)

genre_sim_sorted_ind = genre_sim.argsort()[:,::-1]

percentile = 0.6
m = movies_df['vote_count'].quantile(percentile)
C = movies_df['vote_average'].mean()


def weighted_vote_average(record):
    v = record['vote_count']
    R = record['vote_average']

    return ((v / (v + m)) * R) + ((m / (m + v)) * C)


movies_df['weighted_vote'] = movies_df.apply(weighted_vote_average, axis=1)

def find_sim_moive(df=movies_df,sorted_ind=genre_sim_sorted_ind,title_name='The Godfather',top_n=10):
    title_movie = df[df['title'] == title_name]
    title_index = title_movie.index.values

    # top_n의 2배에 해당하는 쟝르 유사성이 높은 index 추출
    similar_indexes = sorted_ind[title_index, :(top_n * 2)]
    similar_indexes = similar_indexes.reshape(-1)
    # 기준 영화 index는 제외
    similar_indexes = similar_indexes[similar_indexes != title_index]

    # top_n의 2배에 해당하는 후보군에서 weighted_vote 높은 순으로 top_n 만큼 추출
    return df.iloc[similar_indexes].sort_values('weighted_vote', ascending=False)[:top_n]

# similar_moives = find_sim_moive(movies_df,genre_sim_sorted_ind,'The Godfather',10)









