<!-- markdownlint-disable-file first-line-h1 -->
<!-- markdownlint-disable-file no-inline-html -->
<table>
  <thead>
    <tr>
      <th>Placeholders</th>
      <th>Related Flags</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>INDEX</td>
      <td>id/</td>
      <td markdown="1">{% include_relative _ug/placeholders/INDEX.md %}</td>
    </tr>
    <tr>
      <td>INDEX_LIST</td>
      <td>id/</td>
      <td>The INDEX_LIST is a list of INDEX separated by commas. <br>All restrictions to INDEX are relevant.<br/><br><strong>IMPORTANT</strong>: <br>There is a limit of 20 indexes in the list.<br>Do not insert unnecessary commas or spaces.<br>Do not include duplicates in the list.<br><br/><strong>Valid Examples</strong>:<br>1<br>3214<br>3,2,1,4<br><br/><strong>Invalid Examples:</strong><br>,<br>3,2,1,4,<br>3,3,3,3<br>3,,2,1,,,4,<br>3, 2, 1, 4<br>3 2 1 4<br>3/2/1/4<br>1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21</td>
    </tr>
    <tr>
      <td>ITEM_NAME</td>
      <td>n/</td>
      <td>The ITEM_NAME is the term we use to identify an item.<br>ITEM_NAME is a short text.<br/><br><strong>IMPORTANT</strong>: <br>Only English characters, numbers, space, and the following symbols are accepted:<br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 150 characters. Below is a text that is 200 characters long:<br>“Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum vitae purus at nisi aliquet convallis. Aliquam scelerisque in erat ac sodales sed.”<br>ITEM_NAME is unique<br>ITEM_NAME cannot be blank <br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>Potatoes<br>Tomatoes! (Sauce :D)?<br>     <br><br/><strong>Invalid Examples:</strong><br>黃瓜<br>Carrots/Cucumbers<br>Carrots|Cucumbers<br>Carrots\Cucumbers</td>
    </tr>
    <tr>
      <td>TAG_NAME</td>
      <td>n/</td>
      <td>The TAG_NAME is the term we use to identify a tag.<br>TAG_NAME is a short text.<br/><br><strong>IMPORTANT</strong>:<br>Only English characters, numbers, space, and the following symbols are accepted: <br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 30 characters in a TAG_NAME. Below is a text that is 30 characters long:<br>“Lorem ipsum dolor sit posuere.”<br>TAG_NAME is unique<br>TAG_NAME cannot be blank <br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>Food<br>Yummy! (Delicious :D)?<br><br/><strong>Invalid Examples:</strong><br>Одноразовый<br>Food/Condiments<br>Food|Condiments<br>Food\Condiments<br>Food Food Food Food Food Food Food</td>
    </tr>
    <tr>
      <td>QUANTITY</td>
      <td>qty/</td>
      <td>The QUANTITY is the number representing the amount of an item.<br>QUANTITY is a number larger than 0. It has an accuracy of up to 4 decimal places.<br/><br><strong>IMPORTANT</strong>:<br>There is a limit of 10000000 for the quantity.<br>Do not include thousands separators. <br>Do not include mathematical symbols<br><br/><strong>Valid Examples</strong>:<br>12<br>12.1234<br>1234567<br><br/><strong>Invalid Examples:</strong><br>12.12345<br>1,234,567<br>1 + 1<br>1/2<br>π</td>
    </tr>
    <tr>
      <td>UNIT</td>
      <td>unit/</td>
      <td>The UNIT is an optional text indicating the unit of an item.<br>UNIT is a short text.<br/><br><strong>IMPORTANT</strong>:<br>Only English characters, numbers, space, and the following symbols are accepted: <br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 20 characters in a UNIT.<br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>kg<br>Packets<br><br/><strong>Invalid Examples:</strong><br>Containers (1000 grams)<br>Containers/grams<br>Containers|grams<br>Containers\grams</td>
    </tr>
    <tr>
      <td>BOUGHT_DATE</td>
      <td>bgt/</td>
      <td>The BOUGHT_DATE is an optional date indicating when the item was bought.<br>BOUGHT_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
    </tr>
    <tr>
      <td>EXPIRY_DATE</td>
      <td>exp/</td>
      <td>The EXPIRY_DATE is an optional date indicating when the item will expire.<br>EXPIRY_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
    </tr>
  </tbody>
</table>
