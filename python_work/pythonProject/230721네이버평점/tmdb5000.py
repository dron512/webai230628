import pandas as pd
import warnings; warnings.filterwarnings('ignore')

movies =pd.read_csv('./tmdb_5000_movies.csv')
print(movies.shape)
movies.head(1)

movies_df = movies[['id','title', 'genres', 'vote_average', 'vote_count', 'popularity', 'keywords', 'overview']]

pd.set_option('max_colwidth', 100)
print(movies_df[['genres','keywords']][:1])

from ast import literal_eval

movies_df['genres'] = movies_df['genres'].apply(literal_eval)
movies_df['keywords'] = movies_df['keywords'].apply(literal_eval)

movies_df['genres'] = movies_df['genres'].apply(lambda x : [ y['name'] for y in x])
movies_df['keywords'] = movies_df['keywords'].apply(lambda x : [ y['name'] for y in x])
print(movies_df[['genres', 'keywords']][:1])

from sklearn.feature_extraction.text import CountVectorizer

# CountVectorizer를 적용하기 위해 공백문자로 word 단위가 구분되는 문자열로 변환.
movies_df['genres_literal'] = movies_df['genres'].apply(lambda x : (' ').join(x))
count_vect = CountVectorizer(min_df=0, ngram_range=(1,2))
genre_mat = count_vect.fit_transform(movies_df['genres_literal'])
print(genre_mat.shape)

from sklearn.metrics.pairwise import cosine_similarity

genre_sim = cosine_similarity(genre_mat, genre_mat)
print(genre_sim.shape)
print(genre_sim[:2])

genre_sim_sorted_ind = genre_sim.argsort()[:, ::-1]
print(genre_sim_sorted_ind[:1])

def find_sim_movie(df, sorted_ind, title_name, top_n=10):
    # 인자로 입력된 movies_df DataFrame에서 'title' 컬럼이 입력된 title_name 값인 DataFrame추출
    title_movie = df[df['title'] == title_name]

    # title_named을 가진 DataFrame의 index 객체를 ndarray로 반환하고
    # sorted_ind 인자로 입력된 genre_sim_sorted_ind 객체에서 유사도 순으로 top_n 개의 index 추출
    title_index = title_movie.index.values
    similar_indexes = sorted_ind[title_index, :(top_n)]

    # 추출된 top_n index들 출력. top_n index는 2차원 데이터 임.
    # dataframe에서 index로 사용하기 위해서 1차원 array로 변경
    print(similar_indexes)
    similar_indexes = similar_indexes.reshape(-1)

    return df.iloc[similar_indexes]

similar_movies = find_sim_movie(movies_df, genre_sim_sorted_ind, 'The Godfather',10)
print(similar_movies[['title', 'vote_average']])

movies_df[['title','vote_average','vote_count']].sort_values('vote_average', ascending=False)[:10]

C = movies_df['vote_average'].mean()
m = movies_df['vote_count'].quantile(0.6)
print('C:',round(C,3), 'm:',round(m,3))

percentile = 0.6
m = movies_df['vote_count'].quantile(percentile)
C = movies_df['vote_average'].mean()


def weighted_vote_average(record):
    v = record['vote_count']
    R = record['vote_average']

    return ((v / (v + m)) * R) + ((m / (m + v)) * C)


movies_df['weighted_vote'] = movies_df.apply(weighted_vote_average, axis=1)

movies_df[['title','vote_average','weighted_vote','vote_count']].sort_values('weighted_vote', ascending=False)[:10]


def find_sim_movie(df, sorted_ind, title_name, top_n=10):
    title_movie = df[df['title'] == title_name]
    title_index = title_movie.index.values

    # top_n의 2배에 해당하는 쟝르 유사성이 높은 index 추출
    similar_indexes = sorted_ind[title_index, :(top_n * 2)]
    similar_indexes = similar_indexes.reshape(-1)
    # 기준 영화 index는 제외
    similar_indexes = similar_indexes[similar_indexes != title_index]

    # top_n의 2배에 해당하는 후보군에서 weighted_vote 높은 순으로 top_n 만큼 추출
    return df.iloc[similar_indexes].sort_values('weighted_vote', ascending=False)[:top_n]


similar_movies = find_sim_movie(movies_df, genre_sim_sorted_ind, 'The Godfather', 10)
print(similar_movies[['title', 'vote_average', 'weighted_vote']])

similar_movies = find_sim_movie(movies_df, genre_sim_sorted_ind, 'X-Men: First Class', 10)
print(similar_movies[['title', 'vote_average', 'weighted_vote']])

similar_movies = find_sim_movie(movies_df, genre_sim_sorted_ind, 'Mission: Impossible III', 10)
print(similar_movies[['title', 'vote_average', 'weighted_vote']])