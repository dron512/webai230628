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

def find_sim_moive(df=movies_df,sorted_ind=genre_sim_sorted_ind,title_name='The Godfather',top_n=10):
    title_movie = df[df['title']==title_name]
    title_index = title_movie.index.values
    similar_index = sorted_ind[title_index,:(top_n)]
    similar_index = similar_index.reshape(-1)

    return df.iloc[similar_index]

# similar_moives = find_sim_moive(movies_df,genre_sim_sorted_ind,'The Godfather',10)









