import cv2
import matplotlib.pyplot as plt

# b g r
dark = cv2.imread('Dark.png',cv2.IMREAD_COLOR)

print(dark.shape)
print(dark[100,100])

dark[80:120,80:120] = [0,0,0]
roi = dark[30:60,100:120]
dark[0:30,0:20] = roi

dark[:,:,1] = 0
dark[:,:,0] = 0
dark[:,:,2] = 0

plt.imshow(cv2.cvtColor(dark,cv2.COLOR_BGR2RGB))
plt.show()