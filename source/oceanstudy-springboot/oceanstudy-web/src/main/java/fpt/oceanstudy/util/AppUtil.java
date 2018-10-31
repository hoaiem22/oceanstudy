package fpt.oceanstudy.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import fpt.oceanstudy.entity.OsFish;
import fpt.oceanstudy.model.FishModel;
import mksgroup.java.common.BeanUtil;

public class AppUtil {
    
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(AppUtil.class);

    /** Flag to parse data from handsontable: avoid the empty row. */
    final static boolean SKIP_EMPTYROW = true;

    public static Iterable<OsFish> parseFish(FishModel data) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        
        final String[] HEADERS = {"id", "name", "weight", "length", "height", "deep", "age", "img", "video"};

        LOG.info("role data=" + data.getData());
        List<OsFish> reportListRole = (List<OsFish>) BeanUtil.getDataList(data.getData(), HEADERS,
                OsFish.class, SKIP_EMPTYROW, "createdbyUsername", username, "created");

        return reportListRole;
    }
}
