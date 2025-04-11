#!/usr/bin/env python

import os
from urllib import parse

HEADER = """
# 🎯 백준, 프로그래머스, SWEA 문제 풀이 목록
"""

# 백준 등급 이모지 매핑 (예시)
BOJ_TIER_ORDER = {
    "Bronze": "🥉 Bronze",
    "Silver": "🥈 Silver",
    "Gold": "🥇 Gold",
    "Platinum": "🍏 Platinum",
    "Diamond": "💎 Diamond",
    "Ruby": "❤️ Ruby"
}

# 프로그래머스 레벨 이모지 매핑 (예시)
PROGRAMMERS_LEVEL = {
    "0": "🍼 Lv.0",
    "1": "🐣 Lv.1",
    "2": "🐥 Lv.2",
    "3": "🐤 Lv.3",
    "4": "🦉 Lv.4",
    "5": "🦅 Lv.5"
}

# SWEA 단계 이모지 매핑 (예시)
def swea_label(name):
    return f"⭐ {name.upper()}"

def parse_problem_folder(folder_name: str) -> str:
    """
    '1000. A+B' 같은 폴더 이름을 '1000 - A+B' 로 변환.
    폴더명에 '.'이 없으면 그대로 반환.
    """
    if "." in folder_name:
        parts = folder_name.split(".", 1)
        prob_num = parts[0].strip()
        prob_title = parts[1].strip()
        return f"{prob_num} - {prob_title}"
    else:
        return folder_name

def extract_problem_number(folder_name: str) -> int:
    """
    폴더 이름에서 문제 번호를 추출합니다.
    예: "1152. A+B" -> 1152
    폴더 이름에 '.'가 없으면 정렬 시 뒤로 밀리도록 큰 값을 반환.
    """
    if "." in folder_name:
        try:
            num_part = folder_name.split(".")[0].strip()
            return int(num_part)
        except ValueError:
            return float("inf")
    else:
        return float("inf")

def main():
    content = HEADER + "\n"
    
    # 메인 카테고리 이름들
    main_categories = ["백준", "프로그래머스", "SWEA"]
    # 데이터 구조:
    # data[(메인카테고리, 서브카테고리)] = { 문제폴더이름: [파일경로, ...] }
    data = {}
    
    for root, dirs, files in os.walk("."):
        # .git, .github, images 폴더는 건너뛰기
        dirs[:] = [d for d in dirs if d not in (".git", ".github", "images")]
        if root == ".":  # 루트 디렉토리는 건너뜁니다.
            continue
        
        # 경로(root)에서 메인 카테고리가 어느 것에 속하는지 체크
        main_cat = None
        for cat in main_categories:
            if os.sep + cat + os.sep in root or root.endswith(os.sep + cat) or root == "./" + cat:
                main_cat = cat
                break
        if main_cat is None:
            continue
        
        # 메인 카테고리 바로 아래 폴더(또는 그 아래 하위 경로)를 서브 카테고리(sub_cat)로 간주합니다.
        main_path = os.path.join(".", main_cat)
        rel_path = os.path.relpath(root, main_path)  # 예: 'Bronze/1000. A+B' 등
        parts = rel_path.split(os.sep)
        sub_cat = parts[0]  # 예: Bronze 또는 "." (파일이 바로 메인 폴더에 있을 때)
        
        # 문제 폴더가 있다면 (예: "1000. A+B")
        problem_folder = None
        if len(parts) > 1:
            problem_folder = parts[1]
        
        # data 초기화
        if (main_cat, sub_cat) not in data:
            data[(main_cat, sub_cat)] = {}
        
        if problem_folder is not None:
            if problem_folder not in data[(main_cat, sub_cat)]:
                data[(main_cat, sub_cat)][problem_folder] = []
            
            for file in files:
                if file.lower() == "readme.md":  # 문제 설명 파일 제외
                    continue
                full_path = os.path.join(root, file)
                data[(main_cat, sub_cat)][problem_folder].append(full_path)
        else:
            # sub_cat 자체가 문제 폴더인 경우(메인 카테고리 폴더에 바로 파일이 있는 경우)
            pass
    
    # data의 내용을 바탕으로 content 구성 - 각 서브 카테고리(레벨)별로 하나의 표 생성
    for main_cat in main_categories:
        # 해당 메인 카테고리에 해당하는 (main_cat, sub_cat) 키 목록
        keys = [(k, v) for k, v in data.items() if k[0] == main_cat]
        if not keys:
            continue
        
        # 메인 카테고리 헤더
        content += "---\n"
        content += f"## 📚 {main_cat}\n"
        
        # 서브 카테고리(예: Bronze, Silver 등) 정렬
        if main_cat == "백준":
            order = ["Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"]
            keys_sorted = sorted(keys, key=lambda item: order.index(item[0][1]) if item[0][1] in order else 999)
        else:
            keys_sorted = sorted(keys, key=lambda x: x[0][1])
        
        for (mc, sub_cat), problem_map in keys_sorted:
            if sub_cat == ".":
                continue
            
            if mc == "백준":
                tier_title = BOJ_TIER_ORDER.get(sub_cat, f"✅ {sub_cat}")
            elif mc == "프로그래머스":
                tier_title = PROGRAMMERS_LEVEL.get(sub_cat, f"📘 Lv.{sub_cat}")
            elif mc == "SWEA":
                tier_title = swea_label(sub_cat)
            else:
                tier_title = sub_cat

            content += f"### {tier_title}\n"
            content += "| 문제 | 링크 |\n"
            content += "| ----- | ---- |\n"
            
            # 문제 폴더를 문제 번호 기준(숫자)으로 정렬합니다.
            for pfolder, file_list in sorted(problem_map.items(), key=lambda item: extract_problem_number(item[0])):
                parsed_name = parse_problem_folder(pfolder)
                folder_path = os.path.join(".", mc, sub_cat, pfolder)
                content += f"| {parsed_name} | [링크]({parse.quote(folder_path)}) |\n"
            
            content += "\n"
    
    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
