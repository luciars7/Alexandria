<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <body>
   <p><b>ALEXANDRIA LIBRARY </b></p>
   <p><b>Papers: </b></p>
   <table border="1">
      <th>Paper</th>
      <th>Title</th>
      <th>Source</th>
      <th>Author</th>
      <th>Disease</th>
      <th>Device</th>
      <th>Procedure</th>
      <th>Image</th>
      <xsl:for-each select="//Paper">
	      <tr>
	      <td><xsl:value-of select="@id" /></td>
	      <td><xsl:value-of select="Title" /></td>
	      <td><xsl:value-of select="Source" /></td>
	      <td><xsl:value-of select="Author/@name" /></td>
	      <td><xsl:value-of select="Disease/@name" /></td>
	      <td><xsl:value-of select="Device/@name" /></td>
	      <td><xsl:value-of select="Procedure/@name" /></td>
	      <td><xsl:value-of select="Image/@id" /></td>
	      </tr>
      </xsl:for-each>
   </table>
   </body>
   </html>
</xsl:template>

</xsl:stylesheet>