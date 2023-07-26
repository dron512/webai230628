# pip install selenium
from selenium import webdriver
from selenium.webdriver.common.by import By

import time

brow = webdriver.Chrome()
brow.get("http://www.naver.com")
time.sleep(3)
print("test")

try:
    elem = brow.find_element(By.CLASS_NAME, 'service_name')
    elem.click()

    brow.back()
    brow.forward()
    brow.refresh()

except Exception as e:
    print(e)
finally:
    time.sleep(10)
    brow.close()
    brow.quit()



