package Resources.Constant;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerDateModel;

public class Constant {
    public static final String image = System.getProperty("user.dir") + "/src/Resources/Image/";
    
    public static final String customFont = System.getProperty("user.dir") + "/src/Resources/Font";
    public static final Font buttonFont = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font digitFont = new Font("Consolas", Font.PLAIN, 14);
    public static final Font hintFont = new Font("Arial", Font.ITALIC, 12);
    public static final Font notifyFont = new Font("Times New Roman", Font.PLAIN, 12);
    public static final Font titleFont = new Font("Segoe UI", Font.BOLD, 14);

    public static final String[] country = {"Việt Nam", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Yemen", "Zambia", "Zimbabwe"};
    public static final String[] cycleType = {"Một lần", "Hàng tháng", "6 tháng/lần", "Hàng năm"};
    public static final String[] dialogIconPath = {image + "failed.png", image + "alert.png", image + "success.png"};
    public static final String[] ethnic = {"Kinh", "Ba na", "Bố Y", "Brâu", "Bru-Vân Kiều", "Co", "Co Lao", "Cống", "Cơ-ho", "Cơ-tu", "Chăm", "Chơ-ro", "Chu ru", "Chứt", "Dao", "Ê-đê", "Gia-rai", "Giáy", "Gié Triêng", "Hà Nhì", "HMông", "Hoa", "Hrê", "Kháng", "Khơ-me", "Khơ-mú", "La Chí", "La Ha", "La Hủ", "Lào", "Lô Lô", "Lự", "Mạ", "Mảng", "Mnông", "Mường", "Nùng", "Ngái", "Ơ Đu", "Pà Thẻn", "Pu Péo", "Phù Lá", "Ra-glai", "Rơ măm", "Sán Chay", "Sán Dìu", "Si La", "Tà-ôi", "Tày", "Thái", "Thổ", "Xinh-mun", "Xơ-Đăng", "Xtiêng"};
    public static final String[] gender = {"Nam", "Nữ"};

    public static String verticalImageTitle(String img, String title) {
        return "<html><center><img src='file:" + Constant.image + img + "'/><br/>" + title + "</center></html>";
    }

    public SpinnerDateModel getSpinnerDateModel() {
        Calendar calendar = Calendar.getInstance();
        Date initialDate = calendar.getTime();
        Date starDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 10); Date endDate = calendar.getTime();
        return new SpinnerDateModel(initialDate, starDate, endDate, Calendar.YEAR);
    }
}