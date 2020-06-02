<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="contents">
    <div class="table-area">
        <table class="tbl" id="accIpTbl">
            <colgroup>
                <col width="70px;"/>
                <col />
                <col width="130px"/>
                <col width="130px"/>
                <col width="130px"/>
                <col width="130px"/>
            </colgroup>
            <thead>
                <tr>
                    <th>SEQ</th>
                    <th>IP</th>
                    <th>등록신청일</th>
                    <th>상태</th>
                    <th>등록자</th>
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <div class="btn-area">
        <div class="paging-box">
            <a href="javascript:void(0);" class="nav prev_02">처음목록</a>
            <a href="javascript:void(0);" class="nav prev_01">이전목록</a>
            <a href="javascript:void(0);" class="active">1</a>
            <a href="javascript:void(0);">2</a>
            <a href="javascript:void(0);">3</a>
            <a href="javascript:void(0);">4</a>
            <a href="javascript:void(0);" class="nav next_01">처음목록</a>
            <a href="javascript:void(0);" class="nav next_02">이전목록</a>
        </div>
        <button type="button" class="btn btn-primary fr">허용 IP 등록</button>
    </div>

</div>
<script src="/resources/static/js/pageJs/accIpList.js"></script>
