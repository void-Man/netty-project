package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description date 2020/10/12
 */
public class HeartBeatsVo {

    private int seqId;
    // 来源userId
    private String uid;
    // 消息内容
    private String content;

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static HeartBeatsVo proto2HeartBeatsVo(ProtoMsg.MessageHeartBeat messageHeartBeat) {
        HeartBeatsVo heartBeatsVo = new HeartBeatsVo();
        heartBeatsVo.setSeqId(messageHeartBeat.getSeq());
        heartBeatsVo.setContent(messageHeartBeat.getJson());
        heartBeatsVo.setUid(messageHeartBeat.getUid());
        return heartBeatsVo;
    }
}
