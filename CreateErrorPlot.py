import matplotlib.pyplot as plt
import sys

values_array_1 = []
values_array_2 = []
values_array_3 = []
with open(sys.argv[1], "r") as reader:
    lines = reader.readlines()
    

f=open(sys.argv[1],"r") 
for line in f:
    a = line.strip('\n')
    b = a.split(' ')
    values_array_1.append(float(b[0]))
    values_array_2.append(float(b[1]))
    values_array_3.append(float(b[2]))

maxAll = []
maxAll.append(max(values_array_3))
maxAll.append(max(values_array_2))

x_max = max(values_array_1)  + 0.2*max(values_array_1)
y_max = max(maxAll) + 0.2*max(maxAll)

fig = plt.figure()
plt.Axes.set_frame_on
plt.title("Error - Epoch")
plt.plot(values_array_1,values_array_2, color = 'green')
plt.plot(values_array_1, values_array_3, color = 'purple' )

plt.ylabel('error')
plt.xlabel('epoch')
plt.grid(True)
plt.xlim([0, x_max])
plt.ylim([0, y_max])
plt.legend(["Train Data", "Test Data"], loc='best')
plt.savefig("ErrorPlot.png")
plt.show()
