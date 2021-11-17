from bottle import route,run
from sklearn.neural_network import MLPClassifier

@route('/smart_home/<room>/<hour>')
def index(hour,room):
    #x = train_data = [home,hour]
    x = [[0,1],[0,2],[0,3],[0,4],[1,0],[1,1],[1,2],[1,3],[2,0],[2,1],[2,2],[2,3],[3,0],[3,2],[3,3],[3,8],[3,9],[3,10],[3,11]]

    #y = target_data = [message code]
    y = [0,0,1,2,10,11,11,12,20,21,22,22,30,30,30,31,31,32,32]
    clf = MLPClassifier(solver='lbfgs', alpha=1e-5,
                        hidden_layer_sizes=(30, 15),random_state=1,max_iter=10000)

    clf.fit(x, y)

    res = clf.predict([[int(room),int(hour)]])


    return str(res[0])
run(host='localhost',port=8080)
