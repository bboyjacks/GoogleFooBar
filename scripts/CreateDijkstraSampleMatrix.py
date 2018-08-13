
def set_neg(x):
    if x == '':
        return '-1'
    elif x == '\n':
        return '-1'
    else:
        return x.strip();

with open("DjiakstraSampleNode.csv", "r") as file:
    print('{', end='')
    for line in file:
        tokens = line.split(',')
        tokens = list(map(set_neg, tokens))
        print('{', end='')
        print(','.join(tokens), end='')
        print('},\n')
    print('};', end='')
