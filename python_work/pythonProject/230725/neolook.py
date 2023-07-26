# pip install selenium
from selenium import webdriver
import time

brow = webdriver.Chrome()
brow.get("http://www.naver.com")

brow.find_element()

time.sleep(10)

brow.close()
brow.quit()