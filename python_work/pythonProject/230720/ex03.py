# requests, betifulsoap
from selenium import webdriver
import time

# chromedriver
# 압축해제한 웹드라이버의 경로와 파일명 지정
driver = webdriver.Chrome()

# Load Page
# chrome을 띄워 네이버 블로그 페이지를 연다.
driver.get(url='http://rt.molit.go.kr/')
time.sleep(1)

# driver.switch_to()
# driver.close()
time.sleep(3)

driver.exit()
