from selenium import webdriver
import requests
from bs4 import BeautifulSoup
import time

web = webdriver.Chrome()
# headers = {'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36'}
#
url ='https://laftel.net/'

web.get(url)

time.sleep(3)

web.quit()



# resp = requests.get(url,headers)
# # resp = requests.get(url)
# resp.encoding='utf-8'
# html_content = resp.content
#
# soup = BeautifulSoup(html_content, "html.parser")
#
# print(soup)