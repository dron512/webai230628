import mysql.connector

def connect_to_database():
    try:
        connection = mysql.connector.connect(
            host='localhost',
            user='root',
            password='1234',
            database='mypython'
        )
        print("MySQL 데이터베이스에 연결되었습니다.")
        return connection
    except mysql.connector.Error as error:
        print("Error while connecting to MySQL:", error)
        return None

# 데이터를 삽입하는 함수
def insert_data(content):
    connection = connect_to_database()
    try:
        cursor = connection.cursor()
        sql = "INSERT INTO test (idx,content) VALUES (%s,%s)"
        cursor.execute(sql, (0,content))
        connection.commit()
        print("데이터가 성공적으로 삽입되었습니다.")
    except mysql.connector.Error as error:
        print("Error while inserting data into MySQL:", error)
    finally:
        connection.close()


