'''
    파일다운로드
'''
# from urllib import request
#
# request.urlretrieve('https://img1.daumcdn.net/thumb/R150x0/?scode=mtistory2&fname=https://tistory1.daumcdn.net/tistory/4349220/attach/d9bde7fd5c2649679572a61abc35e69a','a.png')
# request.urlretrieve('https://neolook.com/advertisements/main_banner/mad02.jpg?t=1690345360','b.png')
import ex03
import time
from selenium import webdriver
from selenium.webdriver.common.by import By

brows = webdriver.Chrome()
brows.get('https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio')

brows.switch_to.frame('iframeResult')

element = brows.find_element(By.ID,"html")
print(element.text)
element.click()

pelements = brows.find_elements(By.TAG_NAME,"p")
for temp in pelements:
    print(temp.text)
    ex03.insert_data(temp.text)

brows.get('https://www.w3schools.com/images/myw3schoolsimage.jpg')

time.sleep(5)
brows.quit()
