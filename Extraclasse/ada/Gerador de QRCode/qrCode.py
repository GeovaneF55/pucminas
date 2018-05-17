import pyqrcode
import qrtools

def qrCode( code ):
    qr = pyqrcode.create( code )
    qr.png( "matriculas/" + code + ".eps", scale=6 )
    qr = qrtools.QR()
    return

def qrDecode( decode ):
    qr = pyqrcode.create( decode )
    qr = qrtools.QR()
    qr.decode( "matriculas/" + decode + ".eps" )
    True
    print(qr.data)
    return

def process( row , code ):
    vector = row.replace(' ', '').rstrip().split('/')
    matricula = vector[0]
    nome = vector[1]
    if code:
        qrCode( matricula )
    else:
        qrDecode( matricula )
    return

input_path = 'matriculas.in'

with open( input_path, 'rb' ) as f:
    while True:
        row = f.readline()
        if not row: break
        process( row , True )
        process( row , False )
