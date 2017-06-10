package cn.cie.common.exception;

import cn.cie.utils.MsgCenter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super(MsgCenter.NOT_FOUND);
    }
}
