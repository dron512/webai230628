'''
    selenium
    requests
    beatifulsoup
    pip install requests
    pip install bs4

    tag 찾기
    find
    find_all

    selecter 선택자
    select_one
    select_all
'''

import requests
from bs4 import BeautifulSoup

# 목록 내용 가져오기
def doReq(ahref):
    html = requests.get(f'https://neolook.com/zb/{ahref}')
    html.encoding = 'ms949'

    soup = BeautifulSoup(html.text, "html.parser")
    viewsubject = soup.select_one('td.view-subject')
    print(f'{viewsubject.text}\n')
    viewcontent = soup.select_one('td.view-content')
    print(f'{viewcontent.text}\n')

text = requests.get('https://neolook.com/zb/zboard.php?id=post2005')
text.encoding='ms949'

soup = BeautifulSoup(text.text,"html.parser")
trs = soup.find_all('tr')
try:
    for tr in trs[4:]:
        notice = tr.select_one('td.column-no')
        # print(notice)
        hit = tr.select_one('td.column-hit')
        # print(hit)
        subject = tr.select_one('td.column subject')
        # print(subject)
        a = tr.select_one('a')
        # print(a)
        # print(a['href'])
        doReq(a['href'])
        # bsadfa
except Exception as e:
    # print(e)
    pass












