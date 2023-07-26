# pip install selenium
from selenium import webdriver
import time
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys

brow = webdriver.Chrome()
brow.get("https://neolook.com/frame")
time.sleep(3)

try:
    iframe_element = brow.find_element(By.TAG_NAME,'iframe')
    brow.switch_to.frame(iframe_element)

    element = brow.find_element(By.CSS_SELECTOR, "body > div.wrapper > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > form > input.input")
    time.sleep(2)
    element.send_keys("채용")
    element.send_keys(Keys.ENTER)
    arr_a = brow.find_elements(By.TAG_NAME,"a")

    for temp in arr_a:
        print(temp.text)

except Exception as e:
    print(e)
finally:
    time.sleep(10)

    brow.close()
    brow.quit()
