import matplotlib.pyplot as plt

x = []
y = []
with open("../data/output.csv", "r") as file:
    for line in file:
        tokens = line.split(',')

        if (tokens[2].rstrip() == '0'):
            x.append(int(tokens[0]))
            y.append(int(tokens[1]))
plt.xticks(range(20))
plt.yticks(range(20))
plt.scatter(x, y)
plt.show()
