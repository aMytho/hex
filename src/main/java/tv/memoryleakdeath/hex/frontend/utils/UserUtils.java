package tv.memoryleakdeath.hex.frontend.utils;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import tv.memoryleakdeath.hex.backend.dao.user.UserDetailsDao;
import tv.memoryleakdeath.hex.common.pojo.HexUser;
import tv.memoryleakdeath.hex.common.pojo.UserDetails;

public final class UserUtils {

    private UserUtils() {

    }

    public static HexUser getUserDetailsFromSession(HttpServletRequest request) {
        Authentication token = (Authentication) request.getUserPrincipal();
        if (token != null && token.getPrincipal() != null) {
            return (HexUser) token.getPrincipal();
        }
        return null;
    }

    public static String getUserId(HttpServletRequest request) {
        HexUser user = getUserDetailsFromSession(request);
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    public static String getDisplayName(HttpServletRequest request) {
        HexUser user = getUserDetailsFromSession(request);
        if (user != null) {
            return user.getDisplayName();
        }
        return null;
    }

    public static void updateUserDetailsInCurrentSession(HttpServletRequest request, UserDetailsDao userDetailsDao) {
        HexUser user = getUserDetailsFromSession(request);
        if (user == null) {
            return;
        }
        UserDetails details = userDetailsDao.findById(user.getId());
        user.setDisplayName(details.getDisplayName());
        user.setGravatarId(details.getGravatarId());
    }
}
