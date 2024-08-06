package ma.attijari.essearchapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ComplaintSource {

    SELFCARE("Selfcare"),
    BRANCH("Branch"),
    SOCIAL_MEDIA("Social Media"),
    CALL_CENTER("Call Center"),
    E_MAIL("E-mail"),
    WEB_SITE("Web Site"),
    LIVE_CHAT("Live Chat");

    private final String source;
}
