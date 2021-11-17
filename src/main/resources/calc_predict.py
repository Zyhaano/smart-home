# Huzhao Edward
import os

import bottle
import joblib
from bottle import Bottle, request, run
from sklearn.neural_network import MLPClassifier

where_am_i = os.getcwd()
clf_result_file = os.path.join(where_am_i, 'clf_result.pkl')

app = Bottle()


def calc_predict_result(room_idx, hour_num):
    # -1 for error
    predict_result = [-1]
    try:
        clf = joblib.load(clf_result_file)
        room_idx = int(room_idx)
        hour_num = int(hour_num)
        predict_result = clf.predict([[room_idx, hour_num]])
    except IOError:
        # Please generate a clf first.
        predict_result = [-1]
    except TypeError or ValueError:
        # Wrong input
        predict_result = [-1]
    finally:
        return predict_result


@app.route('/smart_home/<room_idx>/<hour_num>')
def res_predict_result(room_idx, hour_num):
    res_msg_code = calc_predict_result(room_idx, hour_num)
    return str(res_msg_code[0])


def parse_clf_cfg(query):
    # solver, hidden_layer_cfg, max_iter
    solver_limit = ['lbfgs', 'sgd', 'adam']
    solver_ipt = str(query.solver)
    if solver_ipt in solver_limit:
        cfg_solver = solver_ipt
    else:
        cfg_solver = 'lbfgs'

    cfg_alpha = 1e-5

    hidden_layer_num_limit = 6
    hidden_layer_ipt = str(query.hl).split(',')
    try:
        if len(hidden_layer_ipt) <= hidden_layer_num_limit:
            cfg_hidden_layer = [int(x) for x in hidden_layer_ipt]
        else:
            cfg_hidden_layer = (30, 15)
    except:
        cfg_hidden_layer = (30, 15)

    cfg_random_state = 1

    max_iter_limit = int(2e4)
    try:
        max_iter_ipt = int(query.iter)
        if max_iter_ipt <= max_iter_limit:
            cfg_max_iter = max_iter_ipt
        else:
            cfg_max_iter = int(1e4)
    except:
        cfg_max_iter = int(1e4)

    return cfg_solver, cfg_alpha, cfg_hidden_layer, cfg_random_state, cfg_max_iter


@app.route('/smart_home/clf/generate', method='GET')
def res_clf_generate():
    cfg_solver, cfg_alpha, cfg_hidden_layer, cfg_random_state, cfg_max_iter = parse_clf_cfg(request.query)
    # x_train = [[room_idx, hour_num], [room_idx, hour_num], ...]
    x_train = [
        [0, 1], [0, 2], [0, 3], [0, 4],
        [1, 0], [1, 1], [1, 2], [1, 3],
        [2, 0], [2, 1], [2, 2], [2, 3],
        [3, 0], [3, 2], [3, 3], [3, 8], [3, 9], [3, 10], [3, 11]
    ]
    # y_train = [msg_code, msg_code, ...]
    y_train = [0, 0, 1, 2, 10, 11, 11, 12, 20, 21, 22, 22, 30, 30, 30, 31, 31, 32, 32]
    clf = MLPClassifier(solver=cfg_solver, alpha=cfg_alpha, hidden_layer_sizes=cfg_hidden_layer,
                        random_state=cfg_random_state, max_iter=cfg_max_iter)
    clf.fit(x_train, y_train)
    joblib.dump(clf, clf_result_file)

    return 'Rebuild clf successfully.'


if __name__ == '__main__':
    run(app=app, host='localhost', port=8080, debug=True)
