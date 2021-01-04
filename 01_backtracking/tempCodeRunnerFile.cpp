    Solution a;
    int queen[4] = {0, 1, 2, 3};
    int n = 4;
    for (string each: a.generateBoard(queen, n)) {
        cout << each << endl;
    }