from Cryptodome.Cipher import AES
import data
import base64
def dec(ciphertext):
	data.call()
	key=base64.b64decode((data.key).encode()) 
	#print(key)
	cipher = AES.new(key, AES.MODE_OFB,b'!\xef\xc0v_2\rfC\xef\xb7\xa0\x80R\xfdV')
	plaintext = cipher.decrypt(ciphertext)
	return plaintext.decode()
def cipher_gen(payl):
	try:
		payl_len=payl[8]
		start=9
		end=start+payl_len
		payload=payl[start:end]
		#print (payload)
		f_data=bytes(payload)
		pt=dec(f_data)
		return pt
	except :
		print("Error")
		return ""
if __name__=="__main__":
	payl=[0x0f, 0x00, 0x0c, 0x05, 0x01, 0x0a, 0x00, 00, 0x08, 0xc0, 0xb7, 0x95, 0x33, 0x04, 0x81, 0xf8, 0x69, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00] 
	pt=cipher_gen(payl)
	print(pt)