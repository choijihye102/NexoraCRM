package project.jennie.nexoracrm.domain;

import lombok.Data;

import java.util.List;


@Data
public class AccListDTO {

    public AccListDTO(int cpg, int totalItems, int pageSize, List<Acc> acclist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double)totalItems/pageSize);  // h2는 ceil함수 지원 안함 이럴땐 class에서 처리해서 보냄
        // 정수 / 정수 -> 정수넘어와서  올림 못함, 한쪽을 double로 형변환함.
        this.acclist = acclist;
        // 페이지네이션 범위계산
        this.stblk = ( (cpg - 1) /10)*10 +1 ;
        this.edblk = Math.min (stblk + 10 -1, cntpg);
    }
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<Acc> acclist;

}




