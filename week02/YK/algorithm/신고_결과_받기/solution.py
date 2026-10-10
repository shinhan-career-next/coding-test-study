def solution(id_list, report, k):
    n = len(id_list)
    answer = [0] * n
    
    id_dict = {}
    report_matrix = [[0] * n for _ in range(n)]
    banned = [0] * n
    
    for i in range(n):
        id_dict[id_list[i]] = i
    
    for item in report:
        reporter, reported_id = item.split()
        
        if report_matrix[id_dict[reported_id]][id_dict[reporter]] == 0:
            report_matrix[id_dict[reported_id]][id_dict[reporter]] = 1
    
    for i in range(n):
        if sum(report_matrix[i]) >= k:
            banned[i] = 1
    
    for i in range(n):
        if banned[i] == 1:
            for j in range(n):
                if report_matrix[i][j] > 0:
                    answer[j] += 1
    
    return answer