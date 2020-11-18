package com.sbs.example.lolHi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ReplyDao;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.util.Util;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;

	public int write(Map<String, Object> param) {
		replyDao.write(param);

		int id = Util.getAsInt(param.get("id"));

		return id;
	}

	public List<Reply> getForPrintReplies(Member actorMember, String relTypeCode, int relId) {
		List<Reply> replies = replyDao.getForPrintReplies(relTypeCode, relId);

		for (Reply reply : replies) {
			if (reply.getExtra() == null) {
				reply.setExtra(new HashMap<>());
			}

			boolean actorCanDelete = false;

			if (actorMember != null) {
				actorCanDelete = actorMember.getId() == reply.getMemberId();
			}

			boolean actorCanModify = actorCanDelete;

			reply.getExtra().put("actorCanDelete", actorCanDelete);
			reply.getExtra().put("actorCanModify", actorCanModify);
		}

		return replies;
	}

	public Reply getReply(int id) {
		return replyDao.getReply(id);
	}

	public void deleteReplyById(int id) {
		replyDao.deleteReplyById(id);
	}

	public void modify(Map<String, Object> param) {
		replyDao.modify(param);
	}
	
	public Reply getForPrintReply(Member actorMember, int id) {
		Reply reply = getReply(id);

		if (reply == null) {
			return null;
		}

		if (reply.getExtra() == null) {
			reply.setExtra(new HashMap<>());
		}

		boolean actorCanDelete = false;

		if (actorMember != null) {
			actorCanDelete = actorMember.getId() == reply.getMemberId();
		}

		boolean actorCanModify = actorCanDelete;

		reply.getExtra().put("actorCanDelete", actorCanDelete);
		reply.getExtra().put("actorCanModify", actorCanModify);

		return reply;
	}
}
