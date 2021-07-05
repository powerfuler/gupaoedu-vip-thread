package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Comment;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class CommentService extends AbstractLoadDataProcessor{
    @Override
    public void load(Context context) {
        //RPC.
        Comment comment=new Comment();
        comment.setName("Mic");
        comment.setContent("商品质量很好");
        context.setComment(comment);
    }
}
