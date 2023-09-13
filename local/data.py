import requests
import json
key=""
dev=False
ecr=False
def call():
        global key
        global dev
        global ecr
        for i in range(10):
                try:
                        url='https://Anisha100.github.io/testing/objects.json'
                        r=requests.get(url,allow_redirects=True)
                        open('objects.json','w').write(r.text)
                        #print(r.text)
                        break

                except:
                        print("Internet Issue")
        f=open('objects.json')
        data=json.load(f)
        key= data["key"]
        dev=data["device_status"]
        ecr=data["encryption_status"]
        #print(key)
call()