package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description date 2020/10/12
 */
public class ChatMessageVo {

    private Long messageId;
    // 来源userId
    private String fromUserId;
    // 目的userId
    private String toUserId;
    // 发送时间
    private Long sendTime;
    // 消息类型
    private Integer messageType;
    // 消息内容
    private String content;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ChatMessageVo proto2ChatMessageVo(ProtoMsg.MessageRequest messageRequest) {
        ChatMessageVo chatMessageVo = new ChatMessageVo();
        chatMessageVo.setToUserId(messageRequest.getToId());
        chatMessageVo.setContent(messageRequest.getContent());
        chatMessageVo.setFromUserId(messageRequest.getFromId());
        chatMessageVo.setMessageType(messageRequest.getMsgType());
        chatMessageVo.setMessageId(messageRequest.getMessageId());
        chatMessageVo.setSendTime(messageRequest.getTime());
        return chatMessageVo;
    }
}
